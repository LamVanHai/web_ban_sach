package com.bookshop.dto;

public class ThongKe {

    private Integer total_input_amount;

    private Integer total_output_amount;

    private  Long total_input_price;

    private  Long total_output_price;

    private  Long revenue;

    public ThongKe(Integer total_input_amount, Integer total_output_amount, Long total_input_price, Long total_output_price, Long revenue) {
        this.total_input_amount = total_input_amount;
        this.total_output_amount = total_output_amount;
        this.total_input_price = total_input_price;
        this.total_output_price = total_output_price;
        this.revenue = revenue;
    }

    public Integer getTotal_input_amount() {
        return total_input_amount;
    }

    public void setTotal_input_amount(Integer total_input_amount) {
        this.total_input_amount = total_input_amount;
    }

    public Integer getTotal_output_amount() {
        return total_output_amount;
    }

    public void setTotal_output_amount(Integer total_output_amount) {
        this.total_output_amount = total_output_amount;
    }

    public Long getTotal_input_price() {
        return total_input_price;
    }

    public void setTotal_input_price(Long total_input_price) {
        this.total_input_price = total_input_price;
    }

    public Long getTotal_output_price() {
        return total_output_price;
    }

    public void setTotal_output_price(Long total_output_price) {
        this.total_output_price = total_output_price;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
}
