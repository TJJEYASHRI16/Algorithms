class Solution {
    //Leetcode 743
    //Time complexity O(E+VlogV)
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer,List<Pair<Integer,Integer>>> map = new HashMap<>();
        //building graph
        for(int[] time : times){
            int u = time[0]-1;
            int v = time[1]-1;
            int w = time[2];
            map.putIfAbsent(u, new ArrayList<>());
            map.get(u).add(new Pair(v,w));
        }
        //initiatlising to infinity
        int[] minTimes = new int[n];
        Arrays.fill(minTimes, Integer.MAX_VALUE);

        //heaps 
        Queue<Pair<Integer,Integer>> minHeap = new PriorityQueue<Pair<Integer,Integer>>(Comparator.comparing(Pair::getKey));
        minHeap.add(new Pair(0,k-1));//weight , node
        minTimes[k-1]=0;

        while(!minHeap.isEmpty()){
            Pair<Integer,Integer> current = minHeap.remove();
            int currWeight = current.getKey();
            int currNode =current.getValue();

            if(currWeight > minTimes[currNode] || !map.containsKey(currNode)){
                continue;
            }

            for(Pair<Integer,Integer> neighbour : map.get(currNode)){
                int weight = neighbour.getValue();
                int nextNode = neighbour.getKey();
                int minWeight = weight+currWeight;
                if(minWeight < minTimes[nextNode]){
                    minTimes[nextNode]=minWeight;
                    minHeap.add(new Pair(minWeight, nextNode));
                }

             
            }
        }

        int ans =0;
        for(int i =0;i<minTimes.length;i++){
            ans = Math.max(ans ,minTimes[i]);

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
