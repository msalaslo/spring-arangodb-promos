package com.msl.data.arangodb.got.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
 
@Edge
public class ChildOf {
 
  @Id
  private String id;
 
  @From
  private Character child;
 
  @To
  private Character parent;
 
  public ChildOf(final Character child, final Character parent) {
    super();
    this.child = child;
    this.parent = parent;
  }
 
  // setter & getter
 
  @Override
  public String toString() {
    return "ChildOf [id=" + id + ", child=" + child + ", parent=" + parent + "]";
  }
 
}
