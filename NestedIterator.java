import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// TC: O(1) as next and hasNext methods are accessible in constant time 
// SC: O(n) in the worst case it can be O(n) where n in height of stack formed as the nested list increases
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((nextEl = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}