package letcode.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCaseOutputUtilsTest {

    @Test
    void formatNodeLists_handlesNullAndChain() {
        assertEquals("[]", TestCaseOutputUtils.formatNodeLists(null));
        ListNode a = new ListNode(1);
        assertEquals("[1]", TestCaseOutputUtils.formatNodeLists(a));
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        assertEquals("[1,2,3]", TestCaseOutputUtils.formatNodeLists(a));
    }

    @Test
    void formatObj_listNodeUsesFormatNodeLists() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        assertEquals("[1,2]", TestCaseOutputUtils.formatObj(head));
    }
}
