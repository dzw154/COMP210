package assn07;

import java.util.*;

import static java.lang.Math.abs;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;
    private int _size = 0;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        Account newAcc = new Account(key, value);
        int kHash = abs(key.hashCode())% _passwords.length;
        if (_passwords[kHash] == null){
            _passwords[kHash] = newAcc;
            _size ++;
        }
        else{
            Account pointer = _passwords[kHash];
            while (pointer!= null){
                if (pointer.getWebsite().equals(key)){
                    pointer.setPassword(value);
                    return;
                }
                if (pointer.getNext() == null){
                    pointer.setNext(newAcc);
                    _size ++;
                    return;
                }
                pointer = pointer.getNext();
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int kHash = abs(key.hashCode())%_passwords.length;
        Account current = _passwords[kHash];
        while (current != null) {
            if (current.getWebsite().equals(key)){
                return (V) current.getPassword();
            }
            current = current.getNext();
        }
        return null;

    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> _websites = new HashSet<>();
        for (int i = 0; i < _passwords.length; i++){
            if (_passwords[i] != null){
                Account current = _passwords[i];
                while (current != null){
                    _websites.add((K) current.getWebsite());
                    current = current.getNext();
                }
            }
        }
        return _websites;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int kHash = abs(key.hashCode())% _passwords.length;
        Account current = _passwords[kHash];

        // If the key does not exist, return null
        if (current == null){
            return null;
        }
        else{
            // If the first item at the index has a matching key
            if (current.getWebsite().equals(key)) {
                V pass = (V) current.getPassword();
                current = current.getNext();
                _size --;
                return pass;
            }
            // Searches for an item at the index with a matching website
            while (current.getNext() != null && current.getNext().getWebsite() != key){
                current = current.getNext();
            }
            if (current.getNext().getWebsite() == null){
                return null;
            }
            else{
                V pass = (V) current.getNext().getPassword();
                current.setNext(current.getNext().getNext());
                _size --;
                return pass;
            }
        }
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> _same = new ArrayList<K> ();
        for (int i = 0; i < _passwords.length; i++){
            if (_passwords[i] != null){
                Account current = _passwords[i];
                while (current != null){
                    if (current.getPassword().equals(value)){
                        _same.add((K) current.getWebsite());
                    }
                    current = current.getNext();
                }
            }
        }
        return _same;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)){
            return true;
        }
        return false;
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (length < 4) {
            targetStringLength = 4;
        }

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
