package loadbalance;

import lombok.Data;
import redis.clients.util.MurmurHash;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName : HashDemo
 * @Author : yq
 * @Date: 2021-03-28
 * @Description : 一致性Hash
 * <p>
 * 一致性hash与普通hash区别：
 * 在增加/删除节点时导致部分缓存失效，并且缓存分布不均，容易引起缓存雪崩
 * 节点的伸缩性比较差
 * <p>
 * 应用场景：缓存
 */
public class HashDemo {

    private TreeMap<Long, Node> nodes;

    //hash算法
    private MurmurHash murmurHash;

    HashDemo(List<Node> nodeList) {
        this.murmurHash = new MurmurHash();
        this.initialize(nodeList);
    }

    /**
     * 不带虚拟节点及加权
     *
     * @param nodeList
     */
    private void initialize(List<Node> nodeList) {
        nodes = new TreeMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            //hash算法可具体定制化
            long hash = murmurHash.hash(node.getName());
            nodes.put(hash, node);
        }
    }

    /**
     * 带虚拟节点及加权
     * 默认160个虚拟节点
     *
     * @param nodeList
     */
    private void initialize1(List<Node> nodeList) {

        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            //默认160个虚拟节点
            for (int j = 0; j < 160 * node.getWeight(); j++) {
                long hash = murmurHash.hash(node.getName());
                nodes.put(hash, node);
            }
        }
    }


    public Node getNode(String key) {
        long hash = murmurHash.hash(key);
        SortedMap<Long, Node> tailMap = nodes.tailMap(hash);
        if (tailMap.isEmpty()) {
            //获取第一个
            return nodes.get(nodes.firstKey());
        } else {
            //获取最近的一个
            return tailMap.get(tailMap.firstKey());
        }
    }
}

@Data
class Node {

    private int weight;

    private String name;

    private String host;

    private int port;

}

