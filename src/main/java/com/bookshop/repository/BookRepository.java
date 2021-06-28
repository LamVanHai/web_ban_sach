package com.bookshop.repository;

import com.bookshop.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findOneById(long id);

    @Query("select e from Book e where e.category.keyWord=:name")
    List<Book> findAllByCategory(@Param("name") String name, Pageable pageable);

    @Query("select e from Book e where e.category.keyWord=:name")
    List<Book> findAllByCategory1(@Param("name") String name);

    //@Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%',:username,'%')")
    @Query("select e from Book e where concat(e.id,e.keyWord,e.name,e.price,e.sale_price) like %?1%")
    List<Book> findByName(String name, Pageable pageable);

    @Query("select count(e.id) from Book e where concat(e.id,e.keyWord,e.name,e.price,e.sale_price) like %?1%")
    List<Book> countByKeyWord(String name);

    @Query("select e.amount from Book e where e.id=:id")
    int countByAmount(@Param("id") Long id);

    @Query("select e from Book e where e.publisher_book.keyWord=:name")
    List<Book> findAllByPublisher(@Param("name") String name, Pageable pageable);

    @Query("select e from Book e where e.publisher_book.keyWord=:name")
    List<Book> countByPublisher(@Param("name") String name);

//    @Query(value = "select b from books b join books_writers bw on b.id=bw.book_id" +
//                    "join writers w on w.id=bw.writer_id where w.slug=?1",nativeQuery=true
//    )
    List<Book> findAllByWriters(String name, Pageable pageable);

    @Query("select e from Book e where e.publisher_book.keyWord=:name")
    List<Book> countByWriters(@Param("name") String name);


    public static void main(String[] args) {
        System.out.println();
    }

}
