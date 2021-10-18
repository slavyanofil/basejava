import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            if (!storage[i].toString().equals(null)) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int currentIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                currentIndex = i;
            }
        }
        Resume[] tempStorage1 = new Resume[currentIndex];
        Resume[] tempStorage2 = new Resume[size() - currentIndex];
        for (int i = 0; i < size() - currentIndex; i++) {
            tempStorage2[i] = storage[i + currentIndex + 1];
        }
        System.arraycopy(storage, 0, tempStorage1, 0, currentIndex);
        System.arraycopy(tempStorage1, 0, storage, 0, currentIndex);
        System.arraycopy(tempStorage2, 0, storage, currentIndex, tempStorage2.length);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            } else break;
        }
        return count;
    }
}
