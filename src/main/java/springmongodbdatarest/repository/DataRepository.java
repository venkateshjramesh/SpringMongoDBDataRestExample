package springmongodbdatarest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springmongodbdatarest.entity.Data;
import springmongodbdatarest.entity.Product;

//@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface DataRepository extends MongoRepository<Data, String> {
    public Data findByText(String text);

}
