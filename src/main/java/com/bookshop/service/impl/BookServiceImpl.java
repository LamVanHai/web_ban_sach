package com.bookshop.service.impl;

import com.bookshop.dto.request.BookRequest;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.ThongKeResponse;
import com.bookshop.entity.*;
import com.bookshop.mapper.BookMapper;
import com.bookshop.repository.*;
import com.bookshop.service.BookService;
import com.bookshop.util.RandomUtil;
import com.bookshop.util.ReflectionUtil;
import com.bookshop.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Order_detailRepository order_detailRepository;

    @Autowired
    private RandomUtil randomUtil;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookResponse> findAll(Pageable pageable) {
        List<Book> books = bookRepository.findAll(pageable).getContent();
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public List<BookResponse> findByKeyWord(String keyWord, Pageable pageable) {
        List<Book> books = bookRepository.findByName(keyWord, pageable);
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public int getTotalItem() {
        return (int) bookRepository.count();
    }


    @Override
    @Transactional
    public BookRequest save(BookRequest bookRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Category category = categoryRepository.findOne(bookRequest.getCategory_id());
        Publisher publisher = publisherRepository.findOne(bookRequest.getPublisher_id());
        Writer writer = writerRepository.findOne(bookRequest.getWriter_id());
        Book book = new Book();
        Set<Writer> writers = new HashSet<>();
        writers.add(writer);

        if (bookRequest.getId() != null) {
            Book oldBook = bookRepository.findOne(bookRequest.getId());
            oldBook.setCategory(category);
            oldBook.setPublisher_book(publisher);
            ReflectionUtil.mapper(bookRequest, oldBook);
            book = oldBook;
            if (UploadFileUtil.fileURL != null) {
                book.setImage(UploadFileUtil.fileURL);//gán file ảnh
            }
            List<Order_detail> order_details = order_detailRepository.findAllByBook_id(book.getId());
            int amount = 0;
            if (order_details != null) {
                for (Order_detail detail : order_details
                ) {
                    if (detail.getOrder3().getStatus() != 3) {
                        amount += detail.getAmount();
                    }
                }
                book.setAmount(book.getInput_amount() - amount);
            }

        } else {
            ReflectionUtil.mapper(bookRequest, book);
            book.setCategory(category);
            book.setAmount(book.getInput_amount());
            book.setPublisher_book(publisher);
            book.setWriters(writers);
            book.setImage(UploadFileUtil.fileURL);

        }

        bookRepository.save(book);
        ReflectionUtil.mapper(book, bookRequest);
        return bookRequest;

    }

    @Override
    public BookRequest findOneById(Long id) {
        Book book = bookRepository.findOne(id);
        BookRequest bookRequest = new BookRequest();
        try {
            ReflectionUtil.mapper(book, bookRequest);
            bookRequest.setCategory_id(book.getCategory().getId());
            bookRequest.setPublisher_id(book.getPublisher_book().getId());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return bookRequest;
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long i : ids) {
            List<Comment> comment = commentRepository.findByBook_Id(i);
            List<Order_detail> order_details = order_detailRepository.findAllByBook_id(i);
            for (Comment comment1 : comment) {
                commentRepository.delete(comment1.getId());
            }
            for (Order_detail order_detail : order_details) {
                order_detailRepository.delete(order_detail.getId());
            }
            bookRepository.delete(i);
        }
    }

    @Override
    public List<BookResponse> findAllKy_Nang_Song() {
        List<Book> books = bookRepository.findAllByCategory1("ky-nang-song");
        List<Book> books1;
        books1 = randomUtil.random(books);
        List<BookResponse> userResponses = books1.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public List<BookResponse> findAllTop8Next() {
        List<Book> books = bookRepository.findAll();
        List<Book> books1;
        books1 = randomUtil.random(books);
        List<BookResponse> userResponses = books1.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return userResponses;
    }

    public static int countName;

    @Override
    public List<BookResponse> findByCategory(String name, Pageable pageable) {
        List<Book> books = bookRepository.findAllByCategory(name, pageable);
        countName = books.size();
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public List<BookResponse> findByPublisher(String name, Pageable pageable) {
        List<Book> books = bookRepository.findAllByPublisher(name, pageable);
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        int a = books.size();
        return bookResponses;
    }

    @Override
    public List<BookResponse> findNewBook() {
        List<Book> books = bookRepository.findAll();
        List<Book> books1 = new ArrayList<>();
        Map<Integer, Book> map = new HashMap<>();
        int k = 1;
        for (int i = 0; i < books.size(); i++) {
            map.put(k++, books.get(i));
        }
        for (int i = map.size(); i > 0; i--) {
            books1.add(map.get(i));
            if (books1.size() == 8) {
                break;
            }
        }
        List<BookResponse> userResponses = books1.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public List<BookResponse> findByWriter(String name, Pageable pageable) {
        List<Book> books = bookRepository.findAllByWriters(name, pageable);
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());

        return bookResponses;

    }

    @Override
    public int countPublisher(String name) {
        List<Book> books = bookRepository.countByPublisher(name);
        return books.size();
    }

    @Override
    public int countByLikeNameBook(String name) {
        List<Book> books = bookRepository.countByKeyWord(name);
        return books.size();
    }

    @Override
    public int countCaterory(String name) {
        List<Book> books = bookRepository.findAllByCategory1(name);
        return books.size();
    }

    @Override
    public BookResponse findById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Book book = bookRepository.findOneById(id);
        BookResponse bookResponse = new BookResponse();
        ReflectionUtil.mapper(book, bookResponse);
//        if(book.getPrice()!=null){
//            bookResponse.setPrice1(CheckUtil.convertVND(book.getPrice()));
//        }
//        if(book.getSale_price()!=null) {
//            bookResponse.setSale_price1(CheckUtil.convertVND(book.getSale_price()));
//        }
        return bookResponse;
    }

    @Override
    public List<BookResponse> findByName(String name, Pageable pageable) {
        List<Book> books = bookRepository.findByName(name, pageable);
        List<BookResponse> bookResponses = books.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public int countByAmount(Long id) {
        int quanty = bookRepository.countByAmount(id);
        return quanty;
    }

    @Override
    public List<BookResponse> findByDiscount(Pageable pageable) {
        List<Book> books = bookRepository.findAll(pageable).getContent();
        List<Book> bookDiscount = new ArrayList<>();
        for (Book book : books
        ) {
            if (book.getSale_price() != null) {
                if (book.getSale_price() > 0) {
                    bookDiscount.add(book);
                }
            }

        }
        List<BookResponse> bookResponses = bookDiscount.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public List<BookResponse> findByTopDiscount() {
        List<Book> books = bookRepository.findAll();
        List<Book> bookDiscount = new ArrayList<>();
        List<Book> bookDiscount1 = new ArrayList<>();

        for (Book book : books
        ) {
            if (book.getSale_price() != null) {
                if (book.getSale_price() > 0) {
                    bookDiscount.add(book);
                }
            }

        }
        Collections.sort(bookDiscount, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getSale_price().compareTo(o1.getSale_price());
            }
        });
        int i = 0;
        for (Book book : bookDiscount
        ) {
            if (i > 8) {
                break;
            }
            bookDiscount1.add(book);
            i++;
        }
        List<BookResponse> bookResponses = bookDiscount1.stream().map(BookMapper::mapToResponse).collect(Collectors.toList());
        return bookResponses;
    }

    @Override
    public int countByDiscount() {
        List<Book> books = bookRepository.findAll();
        int count = 0;
        for (Book bookk : books) {
            if (bookk.getSale_price() != null) {
                if (bookk.getSale_price() > 0) {
                    count++;
                }
            }

        }
        return count;
    }

    @Override
    public List<ThongKeResponse> list(Pageable pageable) {
        List<Book> books = bookRepository.findAll(pageable).getContent();
        List<ThongKeResponse> thongKeResponses = books.stream().map(bookMapper::mapToThongKeResponse).collect(Collectors.toList());
        return thongKeResponses;
    }

    @Override
    public List<ThongKeResponse> list() {
        List<Book> books = bookRepository.findAll();
        List<ThongKeResponse> thongKeResponses = books.stream().map(bookMapper::mapToThongKeResponse).collect(Collectors.toList());
        return thongKeResponses;
    }

}
