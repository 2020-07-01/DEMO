package dataStructure.skiplist;

import java.util.Random;
import java.util.Stack;

/**
 * @ClassName : SkipList
 * @Author : yq
 * @Date: 2021-04-06
 * @Description :
 */
public class SkipList {

    //头节点，入口
    SkipNode headNode;

    //层数
    int hightLevel;

    //随机
    Random random;

    //最大ceng
    final int MAX_LEVEL = 32;

    SkipList() {
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        hightLevel = 0;
    }

    /**
     * 插入操作
     * 1.如果此节点存在则更新值
     * 2.不存在->添加
     * 3.添加之后考虑是否向上添加节点
     * 4.向上添加节点考虑是否超过头节点的高度
     *
     * @param node
     */
    public void add(SkipNode node) {
        int key = node.key;

        SkipNode findNode = search(key);
        //如果此节点存在，则更新值
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }
        //不存在新增
        Stack<SkipNode> stack = new Stack<>();

        SkipNode team = headNode;
        while (team != null) {
            if (team.right == null) {//右侧没有节点，下降
                stack.push(team);
                team = team.down;
            } else if (team.right.key > key) {//如果右侧节点>key
                stack.push(team);
                team = team.down;
            } else {
                team = team.right;
            }
        }

        int level = 1;
        SkipNode downNode = null;
        while (!stack.empty()) {
            team = stack.pop();
            SkipNode nodeTeam = new SkipNode(node.key, node.value);
            nodeTeam.down = downNode;//第一层插入，向下的节点为null
            downNode = nodeTeam;
            if (team.right == null) {//右侧为空，说明插在末尾
                team.right = nodeTeam;
            } else {
                nodeTeam.right = team.right;
                team.right = nodeTeam;
            }
            //考虑是否需要向上
            if (level > MAX_LEVEL) {
                break;
            }
            double num = random.nextDouble();
            if (num > 0.5) {
                //不向上插入
                break;
            }
            level++;
            if (level > hightLevel) {//如果比当前最大高度还要高，需要改变头节点
                hightLevel = level;
                SkipNode highHeadNode = new SkipNode(Integer.MIN_VALUE, num);
                highHeadNode.down = headNode;
                headNode = highHeadNode;
                stack.push(headNode);
            }
        }

    }


    /**
     * 删除节点
     * 不需要考虑层数
     * 依次向右向下遍历，找到待删除的节点进行删除
     * 然后向下遍历，删除所有待删除的节点
     *
     * @param key
     */
    public void delete(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if (team.right == null) {//这层没有节点，下降
                team = team.down;
            } else if (team.right.key == key) {//如果右侧节点为待删除的节点
                team.right = team.right.right;
                team = team.down;//下降一层，然后在下层再找到待删除的节点，继续删除
            } else if (team.right.key > key) {//下降一层查找
                team = team.down;
            } else {
                //节点还在右侧，向右遍历
                team = team.right;
            }
        }
    }

    /**
     * 查找节点
     * 从头节点开始
     * 如果头节点值key相等，则返回
     * 如果右节点的key > 当前key 向下
     * 否则向右
     *
     * @param key
     * @return
     */
    public SkipNode search(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if (team.key == key) {
                return team;
            } else if (team.right == null) {
                //右侧无值，向下
                team = team.down;
            } else if (team.right.key > key) {
                //下降
                team = team.down;
            } else {
                //右侧的值比当前值小
                team = team.right;
            }
        }
        return null;
    }
}


/**
 * 节点
 *
 * @param <T>
 */
class SkipNode<T> {

    int key;
    T value;
    //向右 向下
    SkipNode right, down;

    public SkipNode(int ket, T value) {
        this.key = ket;
        this.value = value;
    }
}
