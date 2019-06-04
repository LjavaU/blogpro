package cn.superhh.blogpro.pojo;

import java.io.Serializable;

public class BlogType implements Serializable {
    private int id;
    private String typeName;
    private int orderNo;
     private Integer blogCount;

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "BlogTypeService{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
