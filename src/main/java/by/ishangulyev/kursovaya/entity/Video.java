package by.ishangulyev.kursovaya.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Video
{
    private Integer id;
    private String name;
    private String resolution;
    private String ratio;
    private Integer brightness;
    private Integer videotypeId;

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
    @Column(name = "resolution", nullable = true, length = 45)
    public String getResolution()
    {
        return resolution;
    }

    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }

    @Basic
    @Column(name = "ratio", nullable = true, length = 45)
    public String getRatio()
    {
        return ratio;
    }

    public void setRatio(String ratio)
    {
        this.ratio = ratio;
    }

    @Basic
    @Column(name = "brightness", nullable = true)
    public Integer getBrightness()
    {
        return brightness;
    }

    public void setBrightness(Integer brightness)
    {
        this.brightness = brightness;
    }

    @Basic
    @Column(name = "videotypeID", nullable = true)
    public Integer getVideotypeId()
    {
        return videotypeId;
    }

    public void setVideotypeId(Integer videotypeId)
    {
        this.videotypeId = videotypeId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (id != null ? !id.equals(video.id) : video.id != null) return false;
        if (name != null ? !name.equals(video.name) : video.name != null) return false;
        if (resolution != null ? !resolution.equals(video.resolution) : video.resolution != null) return false;
        if (ratio != null ? !ratio.equals(video.ratio) : video.ratio != null) return false;
        if (brightness != null ? !brightness.equals(video.brightness) : video.brightness != null) return false;
        if (videotypeId != null ? !videotypeId.equals(video.videotypeId) : video.videotypeId != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (resolution != null ? resolution.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        result = 31 * result + (brightness != null ? brightness.hashCode() : 0);
        result = 31 * result + (videotypeId != null ? videotypeId.hashCode() : 0);
        return result;
    }
}
