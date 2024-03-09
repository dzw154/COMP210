package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if (this._element == null) {
			this._element = element;
		}
		else {
			if (this._element.compareTo(element) == 0) {
				return this;
			}
			if (element.compareTo(this._element) > 0) {
				this._right.insert(element);
			}
			if (element.compareTo(this._element) < 0) {
				this._left.insert(element);
			}
			if (this._right.isEmpty() && element.compareTo(this._element) > 0){
				this._right = new NonEmptyBST<T>(element);
			}
			if (this._left.isEmpty() && element.compareTo(this._element) < 0){
				this._left = new NonEmptyBST<T>(element);
			}
		}
		return this;
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if ((!this._right.isEmpty()) && element.compareTo(this._element) > 0){ // Enters right tree if element is larger than this._element
			this._right = this._right.remove(element);
		}
		else if ((!this._left.isEmpty()) && element.compareTo(this._element) < 0){ // Enters left tree if element is smaller than this._element
			this._left = this._left.remove(element);
		}
		else if (!this._right.isEmpty() && element.compareTo(this._element) == 0){ // Replaces current element with smallest element in right tree
			this._element = this._right.findMin();
			this._right = this._right.getRight();
		}
		else if ((this._right.isEmpty() && this.getHeight() > 0) && element.compareTo(this._element) == 0){ // If right tree is empty replaces current element with largest element in left tree
			this._element = this._left.getElement();
			this._left = this._left.getLeft();
		}
		else if ((this._left.isEmpty() && this._right.isEmpty()) && element.compareTo(this._element) == 0){ // Deletes leaves
			return new EmptyBST<>();
		}
		return this;
	}
	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		if (this.isEmpty()){
			return new EmptyBST<>();
		}
		if (start.compareTo(this._element) < 0){
			this._left = this._left.remove_range(start, end);
		}
		if (end.compareTo(this._element) > 0){
			this._right = this._right.remove_range(start, end);
		}
		if ((start.compareTo(this._element) == 0) && (end.compareTo(this._element) == 0)){
			if (this._left.isEmpty() && this._right.isEmpty()){
				return new EmptyBST<>();
			}
			else if (this._right.isEmpty()){
				return this._left;
			}
			else if (this._left.isEmpty()){
				return this._right;
			}
			else{
				this._element = this._right.findMin();
				this._right = this._right.remove(this._element);
			}
		}

		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if (this._element != null){
			System.out.print(this._element + " ");
			this._left.printPreOrderTraversal();
			this._right.printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (this._element != null) {
			this._left.printPostOrderTraversal();
			this._right.printPostOrderTraversal();
			System.out.print(this._element + " ");
		}
	}

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
