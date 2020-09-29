package com.example.socket.L6_Base.clink.core.ds;

/**
 * Created by mac on 2020-09-28.
 * <p>
 * 带优先级的节点，可用于构成链表，后面用于存储每一帧的数据，把每一桢的数据链接起来形成一个链表的形式
 * <p>
 * 如何设置字符串的优先级别高于文件传入呢？
 */
public class BytePriorityNode<Item> {
    public byte priority;//优先级别，范围【-128～127】
    public Item item;//实际要存储的数据
    public BytePriorityNode<Item> next;//下一个节点的数据

    public BytePriorityNode(Item item) {
        this.item = item;
    }

    public void appendWithPriority(BytePriorityNode<Item> node) {
        if (next == null) {
            next = node;
        } else {
            BytePriorityNode<Item> after = this.next;
            if (after.priority < node.priority) {
                //中间位置插入
                this.next = node;
                node.next = after;
            } else {
                after.appendWithPriority(node);
            }
        }
    }

}
