// Singly Linked List Node
class ListNode<T>(val value: T) {
    var next: ListNode<T>? = null
}

// Function to find midpoint of the linked list
fun <T> findMidpoint(head: ListNode<T>?): ListNode<T>? {
    if (head == null) return null

    var slow = head
    var fast = head

    while (fast != null && fast.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    return slow
}

// Helper function to print the linked list
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

    // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next!!.next = ListNode(3)
    head.next!!.next!!.next = ListNode(4)
    head.next!!.next!!.next!!.next = ListNode(5)

    print("Linked List: ")
    printList(head)

    val midpoint = findMidpoint(head)

    if (midpoint != null) {
        println("Midpoint of the linked list is: ${midpoint.value}")
    } else {
        println("The list is empty")
    }
}
