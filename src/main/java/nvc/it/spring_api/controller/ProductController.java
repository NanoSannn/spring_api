package nvc.it.spring_api.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.it.spring_api.model.Product;
import nvc.it.spring_api.model.Review;
import nvc.it.spring_api.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProduct(){
        List<Product> products = productService.getProducts();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search Succes!");
        map.put("data", products);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search By ID Succes!");
        map.put("data", productService.findById(id));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search By Name Succes!");
        map.put("data", productService.findProductByName(name));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Add Product Succes!");
        map.put("data", productService.addProduct(product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Update Product Succes!");
        map.put("data", productService.updateProduct(id, product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> addReview(@PathVariable String id, @RequestBody Review review){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Update Review Succes!");
        map.put("data", productService.addReview(id, review));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        if (!productService.deleteProduct(id)) {
            map.put("msg", "Error Delete");
            // return new ResponseEntity<Object>(map, HttpStatus.OK);
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            map.put("msg", "Delete Succes!");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
        
    }
}
