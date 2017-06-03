package springmongodbdatarest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springmongodbdatarest.entity.Product;

//@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findByEmailAndText(String email, String text);
}
