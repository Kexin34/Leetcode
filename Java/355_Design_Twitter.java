class Twitter {
    private static int timestamp = 0;
    
    // 我们需要一个映射将 userId 和 User 对象对应起来,用来查询user的userId
    private HashMap<Integer, User> userMap = new HashMap<>();
    
    private static class Tweet {
        private int id;
        private int time;
        private Tweet next;
            
        // 需要传入推文内容（id）和发文时间
        public Tweet(int id, int time){
            this.id = id;
            this.time = time;//发表时间
            this.next = null;
        }
    }
    
    private static class User {
        private int id;
        public Set<Integer> followed;
        public Tweet tweet_head;  // 用户发表的推文链表头结点

        public User(int userId){//constructor
            followed = new HashSet<>();
            this.id = userId;
            this.tweet_head = null;   // 新用户未发表的推文
            follow(id);         //关注一下自己
        }

        public void follow(int userId){
            followed.add(userId);
        }

        public void unfollow(int userId){
            // 不可以取关自己
            if(userId != this.id)
                followed.remove(userId);
        }
        public void post(int tweetId){
            Tweet twt = new Tweet(tweetId, timestamp);
            timestamp++;
            // 将新建的推文插入链表头, 越靠前的推文 time 值越大
            twt.next = tweet_head;
            tweet_head = twt;
        }
    }
    
    
    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        // 若 userId 不存在，则新建
        if(!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));
        
        User u = userMap.get(userId);
        u.post(tweetId);
    }
    
   
    // 本题重点：实现合并 k 个有序链表的算法需要用到优先级队列（Priority Queue）
    // 把优先级队列设为按 time 属性从大到小降序排列，因为 time 越大意味着时间越近，应该排在前面
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if(!userMap.containsKey(userId)) return res;
        // 获得关注列表的用户 Id
        Set<Integer> users = userMap.get(userId).followed;
        
        // 自动通过 tweet_time 属性从大到小排序，容量为 users 的大小
        PriorityQueue<Tweet> pq = 
            new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));
        
        // 先将所有链表头节点插入优先级队列
        for(int id : users){
            Tweet twt = userMap.get(id).tweet_head;
            if (twt == null) continue;
            pq.add(twt);
        }
        
        while (!pq.isEmpty()) {
            if (res.size() == 10) break;    // 最多返回 10 条就够了
            Tweet twt = pq.poll();         // 弹出 time 值最大的（最近发表的）
            res.add(twt.id);

            // 将下一篇 Tweet 插入进行排序
            if (twt.next != null) 
                pq.offer(twt.next);
        }
        return res;
        
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        // 若 follower 不存在，则新建
        if(!userMap.containsKey(followerId)){
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        // 若 followee 不存在，则新建
        if(!userMap.containsKey(followeeId)){
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId == followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

// // Runtime: 9 ms, faster than 76.77% of Java
