package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Memory
{
    private Integer id;
    private String name;
    private String size;
    private Integer memorytypeId;

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
    @Column(name = "size", nullable = true, length = 45)
    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    @Basic
    @Column(name = "memorytypeID", nullable = false)
    public Integer getMemorytypeId()
    {
        return memorytypeId;
    }

    public void setMemorytypeId(Integer memorytypeId)
    {
        this.memorytypeId = memorytypeId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memory memory = (Memory) o;

        if (id != null ? !id.equals(memory.id) : memory.id != null) return false;
        if (name != null ? !name.equals(memory.name) : memory.name != null) return false;
        if (size != null ? !size.equals(memory.size) : memory.size != null) return false;
        if (memorytypeId != null ? !memorytypeId.equals(memory.memorytypeId) : memory.memorytypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (memorytypeId != null ? memorytypeId.hashCode() : 0);
        return result;
    }
}
