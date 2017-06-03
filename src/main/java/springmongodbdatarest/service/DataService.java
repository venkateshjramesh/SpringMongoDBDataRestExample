package springmongodbdatarest.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import springmongodbdatarest.entity.*;
import springmongodbdatarest.repository.DataRepository;
import springmongodbdatarest.repository.FeedRepository;
import springmongodbdatarest.repository.ProductRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by rameve02 on 27-12-2016.
 */


@Service
public class DataService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private FeedRepository feedRepository;

    public Product createProduct(Product product) {

        Product productVal = productRepository.findByEmailAndText(product.getEmail(),product.getText());
        return checkIfExistsAndUpdate(product, productVal);
    }

    private Product checkIfExistsAndUpdate(Product product, Product productVal) {
        try {
            Data dataVal = dataRepository.findByText(product.getText());
            //check for time 15 mins and proceed
            if(dataVal != null &&
                dataVal.getScrap().get(dataVal.getScrap().size() - 1).getCreatedDate().compareTo(new Date()) < 0
                    && (new  Date().getTime() - (dataVal.getScrap().get(dataVal.getScrap().size() - 1).getCreatedDate().getTime())
            ) / (60 * 1000) % 60  < 15){
                return  product;

            }
            List<ScrapDetails> scrapDetailsList = scrapDataFromBing(product);
            if(dataVal == null) {
                createNewData(product, scrapDetailsList);
            }else{
                updateExistingData(product, scrapDetailsList, dataVal);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(productVal == null || (productVal.getId()!=null && productVal.getId().equals(""))) {
            return createNewProduct(product);
        }else{
            productVal.setEmailAttempt(productVal.getEmailAttempt() + 1);
            return productRepository.save(productVal);
        }


    }

    private List<ScrapDetails> scrapDataFromBing(Product product) throws IOException {
        System.out.println("---------" + "https://www.bing.com/search?q="+ product.getText().replaceAll("\\s{2,}", " "));
        Document document = Jsoup.connect("https://www.bing.com/search?q="+ product.getText().replaceAll("\\s{2,}", " ")).get();
        Elements links = document.select(".b_algo");
        List<ScrapDetails> scrapDetailsList = new ArrayList<ScrapDetails>();
        int i=0;
        for (Element link : links) {
            ScrapDetails scrapDetails = new ScrapDetails();
            Elements linkVal = link.select("a[href]");
            // get the value from href attribute
            System.out.println("\nlink : " + linkVal.attr("href"));
            scrapDetails.setUrl(linkVal.attr("href"));
            scrapDetails.setSeq(i);i++;
            scrapDetailsList.add(scrapDetails);
        }
        return scrapDetailsList;
    }

    private void updateExistingData(Product product, List<ScrapDetails> scrapDetailsList, Data dataVal) {
        Scrap scrap = new Scrap();
        scrap.setCreatedDate(new Date());
        scrap.setScrapDetailsList(scrapDetailsList);
        dataVal.setModifiedDate(new Date());
        dataVal.setText(product.getText());
        List<Scrap> scrapList = dataVal.getScrap();
        scrapList.add(scrap);
        dataVal.setScrap(scrapList);
        dataRepository.save(dataVal);
    }

    private void createNewData(Product product, List<ScrapDetails> scrapDetailsList) {
        Scrap scrap = new Scrap();
        scrap.setCreatedDate(new Date());
        scrap.setScrapDetailsList(scrapDetailsList);
        Data data = new Data();
        data.setCreatedDate(new Date());
        data.setModifiedDate(new Date());
        data.setText(product.getText());
        List<Scrap> scrapList = new ArrayList<Scrap>();
        scrapList.add(scrap);
        data.setScrap(scrapList);
        dataRepository.save(data);
    }

    private Product createNewProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        product.setCreatedDate(new Date());
        product.setModifiedDate(new Date());
        product.setEmailAttempt(1);
        return productRepository.save(product);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public Feed createNewFeed(Feed feed) {
        feed.setId(UUID.randomUUID().toString());
        feed.setCreatedDate(new Date());
        feed.setModifiedDate(new Date());
        return feedRepository.save(feed);
    }

    public List<Feed> getFeedBetweenPublishDates(String fromDate,String toDate) throws ParseException {
        //2017-06-03T11:00:08.204Z
        Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(fromDate.replaceAll("T"," ").replaceAll("Z",""));
        Date date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate.replaceAll("T"," ").replaceAll("Z",""));
        return feedRepository.findByPublishedDateBetween(date1,date2);
    }

    public List<Feed> getFeedBySource(String source){
        return feedRepository.findBySource(source);
    }

    public List<Feed> getFeedFromCreationDate(String fromDate, String toDate) throws ParseException {
        Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(fromDate.replaceAll("T"," ").replaceAll("Z",""));
        Date date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate.replaceAll("T"," ").replaceAll("Z",""));
        return feedRepository.findByCreatedDateBetween(date1,date2);
    }

    public List<Feed> getFeedBytags( List<String> tags){
        return feedRepository.findByTagsIn(tags);
    }

    public List<Feed> getFeedBytagsAndSource( List<String> tags,String source){
        return feedRepository.findByTagsInAndSource(tags,source);
    }
}
