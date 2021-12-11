package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cpu
{
    private Integer id;
    private String name;
    private String core;
    private String frequence;
    private String bit;

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
    @Column(name = "core", nullable = true, length = 45)
    public String getCore()
    {
        return core;
    }

    public void setCore(String core)
    {
        this.core = core;
    }

    @Basic
    @Column(name = "frequence", nullable = true, length = 45)
    public String getFrequence()
    {
        return frequence;
    }

    public void setFrequence(String frequence)
    {
        this.frequence = frequence;
    }

    @Basic
    @Column(name = "bit", nullable = true, length = 45)
    public String getBit()
    {
        return bit;
    }

    public void setBit(String bit)
    {
        this.bit = bit;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cpu cpu = (Cpu) o;

        if (id != null ? !id.equals(cpu.id) : cpu.id != null) return false;
        if (name != null ? !name.equals(cpu.name) : cpu.name != null) return false;
        if (core != null ? !core.equals(cpu.core) : cpu.core != null) return false;
        if (frequence != null ? !frequence.equals(cpu.frequence) : cpu.frequence != null) return false;
        if (bit != null ? !bit.equals(cpu.bit) : cpu.bit != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (core != null ? core.hashCode() : 0);
        result = 31 * result + (frequence != null ? frequence.hashCode() : 0);
        result = 31 * result + (bit != null ? bit.hashCode() : 0);
        return result;
    }
}
