package springmongodbdatarest.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by rameve02 on 27-12-2016.
 */
public class Scrap {
    private List<ScrapDetails> scrapDetailsList;
    private Date createdDate;

    public List<ScrapDetails> getScrapDetailsList() {
        return scrapDetailsList;
    }

    public void setScrapDetailsList(List<ScrapDetails> scrapDetailsList) {
        this.scrapDetailsList = scrapDetailsList;
    }



    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Scrap{" +
                "scrapDetailsList=" + scrapDetailsList +
                ", createdDate=" + createdDate +
                '}';
    }
}
