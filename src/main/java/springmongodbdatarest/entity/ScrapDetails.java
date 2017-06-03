package springmongodbdatarest.entity;

import java.util.Date;

/**
 * Created by rameve02 on 27-12-2016.
 */
public class ScrapDetails {
    private Integer seq;
    private String url;


    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        return "Scrap{" +
                "seq=" + seq +
                ", url='" + url + '\'' +
                '}';
    }
}
