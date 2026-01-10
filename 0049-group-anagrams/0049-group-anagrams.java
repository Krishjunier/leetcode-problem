class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> ans=new HashMap<>();
        for(String str:strs){
            char[] s=str.toCharArray();
            Arrays.sort(s);
            String sortstr=new String(s);
            if(!ans.containsKey(sortstr)) ans.put(sortstr,new ArrayList<>());
            ans.get(sortstr).add(str);
        }
        return new ArrayList<>(ans.values());
    }
}