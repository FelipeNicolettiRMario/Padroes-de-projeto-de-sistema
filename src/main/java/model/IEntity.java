package model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedList;

public interface IEntity<T extends Document> {
  void delete(ObjectId id);
  Document read(ObjectId id);
  Document update(T model);
  LinkedList<Document> fetch();
}
