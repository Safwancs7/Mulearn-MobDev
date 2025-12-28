// Singly Linked List Node
class ListNode<T>(val value: T) {
    var next: ListNode<T>? = null
}

// Function to find n-th node from the end (single pass)
fun <T> findNthFromEnd(head: ListNode<T>?, n: Int): ListNode<T>? {
    if (head == null || n <= 0) return null

    var fast = head
    var slow = head

    // Move fast pointer n steps ahead
    for (i in 1..n) {
        if (fast == null) return null  // n > length
        fast = fast.next
    }

    // Move both pointers until fast reaches the end
    while (fast != null) {
        fast = fast.next
        slow = slow?.next
    }

    return slow
}

// Helper function to print linked list
fun <T> printList(head: ListNode<T>?) {
    var current = head
    while (current != null) {
        print("${current.value} -> ")
        current = current.next
    }
    println("null")
}

// Main function (TESTING)
fun main() {

    // Create linked list: 10 -> 20 -> 30 -> 40 -> 50
    val head = ListNode(10)
    head.next = ListNode(20)
    head.next!!.next = ListNode(30)
    head.next!!.next!!.next = ListNode(40)
    head.next!!.next!!.next!!.next = ListNode(50)

    print("Linked List: ")
    printList(head)

    val n = 3
    val result = findNthFromEnd(head, n)

    if (result != null) {
        println("The $n-th element from the end is: ${result.value}")
    } else {
        println("Invalid n or list is too short")
    }
}
