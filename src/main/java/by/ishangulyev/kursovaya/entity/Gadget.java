package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gadget
{
    private Integer id;
    private String name;
    private Integer cpuId;
    private Integer memoryId;
    private Integer price;
    private Integer batteryId;
    private Integer videoId;
    private Integer audioId;
    private Integer categoryId;

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
    @Column(name = "cpuID", nullable = true)
    public Integer getCpuId()
    {
        return cpuId;
    }

    public void setCpuId(Integer cpuId)
    {
        this.cpuId = cpuId;
    }

    @Basic
    @Column(name = "memoryID", nullable = true)
    public Integer getMemoryId()
    {
        return memoryId;
    }

    public void setMemoryId(Integer memoryId)
    {
        this.memoryId = memoryId;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    @Basic
    @Column(name = "batteryID", nullable = true)
    public Integer getBatteryId()
    {
        return batteryId;
    }

    public void setBatteryId(Integer batteryId)
    {
        this.batteryId = batteryId;
    }

    @Basic
    @Column(name = "videoID", nullable = true)
    public Integer getVideoId()
    {
        return videoId;
    }

    public void setVideoId(Integer videoId)
    {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "audioID", nullable = true)
    public Integer getAudioId()
    {
        return audioId;
    }

    public void setAudioId(Integer audioId)
    {
        this.audioId = audioId;
    }

    @Basic
    @Column(name = "categoryID", nullable = true)
    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gadget gadget = (Gadget) o;

        if (id != null ? !id.equals(gadget.id) : gadget.id != null) return false;
        if (name != null ? !name.equals(gadget.name) : gadget.name != null) return false;
        if (cpuId != null ? !cpuId.equals(gadget.cpuId) : gadget.cpuId != null) return false;
        if (memoryId != null ? !memoryId.equals(gadget.memoryId) : gadget.memoryId != null) return false;
        if (price != null ? !price.equals(gadget.price) : gadget.price != null) return false;
        if (batteryId != null ? !batteryId.equals(gadget.batteryId) : gadget.batteryId != null) return false;
        if (videoId != null ? !videoId.equals(gadget.videoId) : gadget.videoId != null) return false;
        if (audioId != null ? !audioId.equals(gadget.audioId) : gadget.audioId != null) return false;
        if (categoryId != null ? !categoryId.equals(gadget.categoryId) : gadget.categoryId != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cpuId != null ? cpuId.hashCode() : 0);
        result = 31 * result + (memoryId != null ? memoryId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (batteryId != null ? batteryId.hashCode() : 0);
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        result = 31 * result + (audioId != null ? audioId.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
