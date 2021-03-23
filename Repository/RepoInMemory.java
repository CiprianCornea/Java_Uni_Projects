package Repository;

import Domain.Entity;
import Exceptions.InvalidDataException;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class RepoInMemory<T extends Entity> {
    protected Map<Object, T> storage;

    public RepoInMemory() {
        this.storage = new Hashtable<>();
    }

    public Collection<T> getAll() {
        return this.storage.values();
    }

    public boolean verifyIfInRepo(T element) {
        return this.storage.containsKey(element.getId());
    }

    public void add(T element) {
        if (!this.verifyIfInRepo(element)) {
            this.storage.put(element.getId(), element);
        } else
            throw new InvalidDataException("This item was already stored!");
    }

    public void delete(T element) {
        if (this.verifyIfInRepo(element)) {
            this.storage.remove(element.getId());
        } else
            throw new InvalidDataException("This item is not stored!");
    }

    public void update(T toUpdate, T toReplace) {
        if (this.verifyIfInRepo(toUpdate)) {
            this.storage.remove(toUpdate.getId());
            this.storage.put(toReplace.getId(), toReplace);
        } else
            throw new InvalidDataException("This item you want to update is not stored!");
    }

    public T getById(String id) {
        for (T t : this.storage.values()) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        throw new InvalidDataException("There is no item with this id!");
    }
}
