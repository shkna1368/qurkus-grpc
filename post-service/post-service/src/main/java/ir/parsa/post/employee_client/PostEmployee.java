package ir.parsa.post.employee_client;

import ir.parsa.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostEmployee {

  private   Post post;
 private    Employee employee;

}
