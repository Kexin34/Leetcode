public interface {
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the second.
     */
    int compare(T o1, T o2);
}

返回值说的很清楚，当第一个元素小于第二个元素时返回负数，相等时返回0，否则返回整数。
如果按照以上描述实现compare方法，则会按升序排序，如果正好反过来则是降序排序。
有一点很重当就是，如果要保证算法稳定性，则当两个比较数相同时一定要按要求返回0.
以下是两种常用当写法:
(三种Comparator均实现了升序排序，c1，c2是稳定的，c3不是稳定的。)

Comparator c1 = new Comparator {
  @Override
  public void compare(int o1, int o2) {
    if (o1 == o2) return 0;
    else if (o1 < o2) return -1;
    else return 1;
  }
}

Comparator c2 = new Comparator {
  @Override
  public void compare(int o1, int o2) {
    return o2 - o1;
  }
｝

Comparator c3 = new Comparator {
  @Override
  public void compare(int o1, int o2) {
    return o1 < o2 ? -1 : 1;
  }
}