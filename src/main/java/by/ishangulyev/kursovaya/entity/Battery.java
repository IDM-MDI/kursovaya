package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Battery
{
    private Integer id;
    private String name;
    private Integer mah;

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
    @Column(name = "name", nullable = true, length = 45)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "mah", nullable = true)
    public Integer getMah()
    {
        return mah;
    }

    public void setMah(Integer mah)
    {
        this.mah = mah;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Battery battery = (Battery) o;

        if (id != null ? !id.equals(battery.id) : battery.id != null) return false;
        if (name != null ? !name.equals(battery.name) : battery.name != null) return false;
        if (mah != null ? !mah.equals(battery.mah) : battery.mah != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mah != null ? mah.hashCode() : 0);
        return result;
    }
}
