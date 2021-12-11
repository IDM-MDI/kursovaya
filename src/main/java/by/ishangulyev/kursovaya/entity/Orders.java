package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders
{
    private Integer id;
    private Integer cartId;
    private Integer gadgetId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "cartID", nullable = true)
    public Integer getCartId()
    {
        return cartId;
    }

    public void setCartId(Integer cartId)
    {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "gadgetID", nullable = true)
    public Integer getGadgetId()
    {
        return gadgetId;
    }

    public void setGadgetId(Integer gadgetId)
    {
        this.gadgetId = gadgetId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != null ? !id.equals(orders.id) : orders.id != null) return false;
        if (cartId != null ? !cartId.equals(orders.cartId) : orders.cartId != null) return false;
        if (gadgetId != null ? !gadgetId.equals(orders.gadgetId) : orders.gadgetId != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + (gadgetId != null ? gadgetId.hashCode() : 0);
        return result;
    }
}
