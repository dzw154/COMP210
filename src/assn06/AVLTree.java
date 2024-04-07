package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // Done
         AVLTree<T>  newRoot = this._right;
         this._right = newRoot._left;
         this.updateHeightandSize();
         newRoot._left = this;
         newRoot.updateHeightandSize();
         return newRoot;
     }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // Done
         AVLTree<T> newRoot = this._left;
         this._left = newRoot._right;
         this.updateHeightandSize();
         newRoot._right = this;
         newRoot.updateHeightandSize();
         return newRoot;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        // Compute Balance Factor here - difference between left and right subtree heights
        // TODO
        if (isEmpty()) {
            _value = element;
            _height++;
            _size++;
            return this;
        }
        if (_value.compareTo(element) > 0) {
            if (_right == null){
                _right = new AVLTree<T>();
                _right.setValue(element);
            }
            else{
                _right = (AVLTree<T>) _right.insert(element);
            }
        } else if (_value.compareTo(element) < 0) {
            if (_left == null){
                _left = new AVLTree<T>();
                _left.setValue(element);
            }
            else{
                _left = (AVLTree<T>) _left.insert(element);
            }
        } else {
            return this;
        }
        this.updateHeightandSize();
        this.rotate();
        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
         // when 2 children, replace with minimum from right subtree
    	// TODO
        if (isEmpty()){
            return null;
        }
        if (_value.compareTo(element) > 0){
            _right = (AVLTree<T>) _right.remove(element);
        } else if (_value.compareTo(element) < 0){
            _left = (AVLTree<T>) _left.remove(element);
        }
        else{
            if (_left == null){
                return _right;
            } else if (_right == null) {
                return _left;
            }
            _value = _right.findMin();
            _right = (AVLTree<T>) _right.remove(_value);
            }
        this.updateHeightandSize();
        this.rotate();
        return this;
     }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // Done
        if (_left == null) {
            return _value;
        }

        return _left.findMin();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // Done
        if (_right == null) {
            return _value;
        }

        return _right.findMax();
    }

    @Override
    public boolean contains(T element) {
    	// TODO

        return false;
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO

        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }
    private void  updateHeightandSize(){
         if (_right == null && _left == null){
             _height ++;
             _size ++;
         } else if (_right == null) {
             _height = _left.height() + 1;
         }
         else if (_left == null){
             _height = _right.height() + 1;
         }
         else {
             if (_right.height() >= _left.height()) {
                 _height = _right.height() + 1;
             } else {
                 _height = _left.height() + 1;
             }

             _size = _left.size() + _right.size();
         }
        }
    private void setValue(T element){
         _value = element;
    }
    private void rotate(){
         int bf = this.getBF();
         if (bf > 1){
             if (_left.getBF() < 0){
                 _left = _left.rotateLeft();
             }
             this.rotateRight();
         }
         if (bf < -1){
             if (_right.getBF() > 0){
                 _right = _right.rotateRight();
             }
             this.rotateLeft();
         }
    }
    private int getBF(){
         if (_left == null && _right == null){
             return 0;
         } else if (_left == null) {
             return -_right.height();
         } else if (_right == null) {
             return _left.height();
         }
         return _left.height() - _right.height();
    }
}


