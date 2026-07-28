package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.*;

/**
 * @program: Leetcode
 * @description: 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，
 * 其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。
 * 账户本身可以以任意顺序返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/accounts-merge 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-18 09:19
 */
public class N_721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /*
        并查集的应用

            public int[] parent;
            public String[]  owners, emails;
            public Map<String, Integer> emailToId;
            int emailId = 0;

            public List<List<String>> accountsMerge(List<List<String>> accounts) {

                int n = accounts.size() * 9;
                parent  =  new int[n];
                owners  =  new String[n];
                emails  =  new String[n];
                emailToId = new HashMap<>(n);

                for(int i = 0; i < n; i++)
                    parent[i] = i;

                for(List<String>account: accounts){
                    String owner = account.get(0);
                    int first = getId(account.get(1), owner);
                    for(int i = 2; i < account.size(); i++)
                         union(first, getId(account.get(i), owner));

                }

                int size = emailId;
                List<String> [] merge = new List[size];
                for(int i = 0; i < size; i++){
                    int parent = find(i);
                    if(merge[parent] == null)
                        merge[parent] = new ArrayList<>();

                    merge[parent].add(emails[i]);
                }

                List<List<String>> result=new ArrayList<>();
                for(int i = 0 ; i < size; i++){
                    if(merge[i] == null)
                        continue;

                    Collections.sort(merge[i]);
                    merge[i].add(0, owners[i]);
                    result.add(merge[i]);
                }
                return result;
            }

            public int getId(String email, String owner){

                Integer id = emailToId.get(email);
                if(id == null){
                    id = emailId++;
                    emailToId.put(email, id);
                }

                owners[id] = owner;
                emails[id] = email;
                return id;
            }

            private int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            private void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY)
                    return;

                parent[rootX] = rootY;
            }
         */
        List<Set<String>> unionFindList = new ArrayList<>();
        Set<Integer> mergeIndexSet = new TreeSet<>((o1, o2) -> o2 - o1);
        Map<String, String> email2AccountMap = new HashMap<>();
        Set<String> unionFindSet;
        String account;
        String email;
        for (List<String> data : accounts) {
            mergeIndexSet = new TreeSet<>((o1, o2) -> o2 - o1);
            account = data.get(0);
            for (int i = 1; i < data.size(); i++) {
                email = data.get(i);
                email2AccountMap.put(email, account);
                for (int index = 0; index < unionFindList.size(); index++) {
                    if (unionFindList.get(index).contains(email)) {
                        mergeIndexSet.add(index);
                        break;
                    }
                }
            }
            if (mergeIndexSet.size() > 1 || mergeIndexSet.size() == 0) {
                unionFindSet = new HashSet<>();
                for (Integer index : mergeIndexSet) {
                    unionFindSet.addAll(unionFindList.get(index));
                    unionFindList.remove(index.intValue());
                }
                unionFindList.add(unionFindSet);
            } else {
                unionFindSet = unionFindList.get(mergeIndexSet.iterator().next());
            }
            for (int i = 1; i < data.size(); i++) {
                unionFindSet.add(data.get(i));
            }
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> item;
        for (Set<String> unionFind : unionFindList) {
            unionFind.add("");
            item = new ArrayList<>(unionFind);
            Collections.sort(item);
            item.set(0, email2AccountMap.get(item.get(1)));
            ans.add(item);
        }
        return ans;
    }


}
