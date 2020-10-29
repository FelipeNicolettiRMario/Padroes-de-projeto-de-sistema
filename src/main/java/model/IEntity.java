package model;

import org.bson.Document;

import org.bson.types.ObjectId;

public interface IEntity<T extends Document> {
  T create(T model);

  void delete(ObjectId id);

  T read(ObjectId id);

  T update(T model);
}
