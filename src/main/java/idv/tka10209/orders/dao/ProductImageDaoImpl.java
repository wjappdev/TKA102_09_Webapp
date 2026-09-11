package idv.tka10209.orders.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import idv.tka10209.orders.entity.ProductImage;
import idv.tka10209.util.HibernateUtil;

public class ProductImageDaoImpl {
    private SessionFactory factory = HibernateUtil.getSessionFactory();;

    public ProductImageDaoImpl() {
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public void save(ProductImage productImage) {
        getSession().persist(productImage);
    }

    public ProductImage findById(Integer imageId) {
        return getSession().get(ProductImage.class, imageId);
    }
}
