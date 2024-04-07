package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    public List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient patient = new Patient(value);
        _heap.add(_heap.size(), patient);
        bubbleUp(size()-1);
    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient(value, priority);
        _heap.add(_heap.size(), patient);
        bubbleUp(_heap.size()-1);
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if (_heap.size() == 0){
            return null;
        } else if (_heap.size() == 1) {
            V value = _heap.get(0).getValue();
            _heap.remove(0);
            return value;
        }
        else{
            V value = _heap.get(0).getValue();
            _heap.set(0, _heap.get(_heap.size()-1));
            _heap.remove(_heap.size()-1);
            bubbleDown(0);
            return value;

        }
    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
        return _heap.get(0).getValue();
        }


    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        V val = value;
        P newPrio =  newPriority;
        for (int i = 0; i < _heap.size(); i++){
            V patVal =  _heap.get(i).getValue();
            if (val.equals(patVal)){
                P oldPrio = _heap.get(i).getPriority();
                _heap.set(i, new Patient<V, P>(value,newPriority));
                if(newPrio.compareTo(oldPrio)<0){
                    bubbleDown(i);
                }
                if (newPrio.compareTo(oldPrio)>0){
                    bubbleUp(i);
                }


            }
        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (int i = 0; i < initialEntries.length; i++){
            _heap.add(initialEntries[i]);
            bubbleUp(_heap.size()-1);
        }
    }

    public int bubbleUp(int index){
        if (index == 0){
            return index;
        }
        else{
            Prioritized <V, P> child = _heap.get(index);
            Prioritized<V, P> parent = _heap.get((index-1)/2);
            if (child.getPriority().compareTo(parent.getPriority()) > 0){
                _heap.set((index-1)/2, child);
                _heap.set(index,parent);
                return (bubbleUp((index-1)/2));
            }
            else {
                return index;
            }
        }
    }

    private void bubbleDown(int index){
        Prioritized<V,P> parent = _heap.get(index);
        if (!hasRightChild(index) && !hasLeftChild(index)){
            return;
        }
        else if (!hasRightChild(index)){
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            if (leftChild.getPriority().compareTo(parent.getPriority()) > 0){
                _heap.set(index,leftChild);
                _heap.set(leftChildInd(index),parent);
                bubbleDown(leftChildInd(index));
            }
            else
                return;
        }
        else{
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(index));
            if (leftChild.getPriority().compareTo(parent.getPriority()) < 0 || rightChild.getPriority().compareTo(parent.getPriority()) < 0){
                if (leftChild.getPriority().compareTo(rightChild.getPriority()) <= 0){
                    _heap.set(index, leftChild);
                    _heap.set(leftChildInd(index),parent);
                    bubbleDown(leftChildInd(index));
                }
                else {
                    _heap.set(index, rightChild);
                    _heap.set(rightChildInd(index),parent);
                    bubbleDown(rightChildInd(index));
                }
            }
            else return;
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
    static int parentInd(int index){
        return ((index-1)/2);
    }

    static int rightChildInd(int index) {
        return (2*index+2);
    }

    static int leftChildInd(int index){
        return (2*index +1);
    }

    boolean hasLeftChild(int index){
        return (validIndex(leftChildInd(index)));
    }

    boolean hasRightChild(int index){
        return (validIndex(rightChildInd(index)));
    }

    boolean validIndex(int index){
        if ((index >= 0) && (index <= _heap.size()-1)){
            return true;
        }
        else
            return false;
    }

}
