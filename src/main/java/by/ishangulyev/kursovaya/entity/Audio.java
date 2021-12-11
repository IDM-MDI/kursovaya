package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Audio
{
    private Integer id;
    private String name;
    private Integer audiotypeId;
    private Integer frequency;

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
    @Column(name = "audiotypeID", nullable = true)
    public Integer getAudiotypeId()
    {
        return audiotypeId;
    }

    public void setAudiotypeId(Integer audiotypeId)
    {
        this.audiotypeId = audiotypeId;
    }

    @Basic
    @Column(name = "frequency", nullable = true)
    public Integer getFrequency()
    {
        return frequency;
    }

    public void setFrequency(Integer frequency)
    {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audio audio = (Audio) o;

        if (id != null ? !id.equals(audio.id) : audio.id != null) return false;
        if (name != null ? !name.equals(audio.name) : audio.name != null) return false;
        if (audiotypeId != null ? !audiotypeId.equals(audio.audiotypeId) : audio.audiotypeId != null) return false;
        if (frequency != null ? !frequency.equals(audio.frequency) : audio.frequency != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (audiotypeId != null ? audiotypeId.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }


}
