package springmongodbdatarest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import springmongodbdatarest.entity.Feed;
import springmongodbdatarest.entity.Product;

import java.util.Date;
import java.util.List;

public interface FeedRepository extends MongoRepository<Feed, String> {

    //@Query("{'publishedDate': {$gte: ?0, $lte:?1 }}")
    public List<Feed> findByPublishedDateBetween(String fromDate, String toDate);

    public List<Feed> findByCreatedDateBetween(String fromDate, String toDate);

    public List<Feed> findBySource(String source);

    public List<Feed> findByTagsIn(List<String> arrayValues);

    public List<Feed> findByTagsInAndSource(List<String> arrayValues, String source);
}
