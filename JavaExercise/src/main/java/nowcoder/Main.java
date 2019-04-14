package nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName:  Main   
 * @Description:京东笔试
 * @author: caiji
 * @date: 2019年4月13日 下午8:38:18
 */
public class Main {

	public static void main(String[] args) {

		int m;
		Scanner input = new Scanner(System.in);
		m = input.nextInt();

		ArrayList<String> list = new ArrayList<String>();

		int i = 0;
		while (i <= (m + 1)) {
			i++;
			list.add(input.nextLine());
		}

		//字符串T
		String T = list.remove(list.size() - 1);
		int count = 0;
		for (int j = 1; j < list.size(); j++) {
			//如果存在此元素，
			if (T.contains(list.get(j))) {
				count++;
				int a = T.indexOf(list.get(j));
				for (int k = a; k <= list.get(j).length(); k++) {
					list.set(k, "0");
				}
			}

		}

		System.out.println(count);

	}

}
