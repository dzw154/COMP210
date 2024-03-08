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
		if ((!this._right.isEmpty()) && element.compareTo(this._element) > 0){
			this._right.remove(element);
		}
		if ((!this._right.isEmpty()) && element.compareTo(this._element) < 0){
			this._left.remove(element);
		}
		if (!this._right.isEmpty() && element.compareTo(this._element) == 0){
			this._element = this._right.findMin();
			this._right = this._right.getRight();
		}
		if (this._right.isEmpty() && element.compareTo(this._element) == 0){
			this._element = this._left.getElement();
			this._left = this._left.getLeft();
		}
		if ((this._left.isEmpty() && this._right.isEmpty()) && element.compareTo(this._element) == 0){
			this._element = null;
		}
		return this;
	}
	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {

		return null;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
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
