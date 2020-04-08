package com.ggxiaozhi.lib.class14;

import java.util.HashMap;

/**
 * @Description: 这个没有具体的代码 主要是对Hash函数的总结
 * @Author: ggxz
 * @CreateDate: 2020/4/8 21:47
 * @UpdateUser:
 * @UpdateDate: 2020/4/8 21:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HashCode {

    /**
     * 对于hash函数的设定 我们都是通过转成一个大整数 然后去 %M (对大整数取模M)  通常我们会M的这个数是素数  具体的数值范围对应的M取值 网上有专门的研究形成的表
     * 这里的M 就是hash函数中我们取M的数组的大小
     *
     * 我们可以将基本数据类型 ：负数偏移 对与浮点数可以移位变成整数
     * 对于字符串 我们也可以这么处理 比如：
     * 166	=	1	*	10^2	+	6	*	10^1	+	6	*	10^0
     * 对于字符串"code"
     * code =	c	*	26^3	+	o	*	26^2	+	d	*	26^1	+	e	*	26^0
     * code =	c	*	B^3	+	o	*	B^2	+	d	*	B^1	+	e	*	B^0
     * hash(code) =	(	c	*	B^3	+	o	*	B^2	+	d	*	B^1	+	e	*	B^0	)	%	M
     * hash(code) =	(	c	*	B^3	+	o	*	B^2	+	d	*	B^1	+	e	*	B^0	)	%	M 这里是放置 b^N 方 也就是当N特别大的时候这个计算也是耗时的 对于hash函数我们的目的就是O(1) 随即访问
     * hash(code) =	((((c	*	B)	+	o)	*	B	+	d)	*	B	+	e)	%	M 化简后这里还可能存在一个问题 就是 最后%M 这钱的结果会出现整型溢出
     * hash(code) =	((((c	%	M)	*	B	+	o)	%	M	*	B	+	d)	%	M	*	B	+	e)	%	M  所以这样优化
     *
     * 转成代码
     * int	hash	=	0
     * for(int	i	=	0	;	i	<	s.length()	;	i	++)
     * 				hash	=	(hash	*	B	+	s.charAt(i))	%	M
     * hash(code) =	((((c	%	M)	*	B	+	o)	%	M	*	B	+	d)	%	M	*	B	+	e)	%	M
     *
     * 复合类型 转成整型处Date:	year,	month,	day
     * hash(date) =	(((date.year%M)*B	+	date.month)%M*B	+	date.day)%M
     * hash(code) =	((((c	%	M)	*	B	+	o)	%	M	*	B	+	d)	%	M	*	B	+	e)	%	M
     *
     *
     *
     *
     * 转成整型处理 并不是唯⼀的⽅法！
     * 原则
     * 1.	⼀致性：如果a==b，则hash(a)	==	hash(b)   但是两个哈希函数的结果相同 对应的a和b不一定是一个 因为一个哈希值可能对于多个元素 这是就是哈希冲突
     * 2.	⾼效性：计算⾼效简便
     * 3.	均匀性：哈希值均匀分布  这也是设计Hash函数的关键
     *
     *
     * //TODO 哈希冲突的处理 链地址
     * HashMap	就是⼀个	TreeMap	数组
     * HashSet	就是⼀个	TreeSet	数组
     * Java8之前，每⼀个位置对应⼀个链表
     * Java8开始，当哈希冲突达到⼀定程度
     * 每⼀个位置从链表转成红⿊树
     *
     * 总共有M个地址
     * 如果放⼊哈希表的元素为N
     * 如果每个地址是链表：O(N/M)
     * 如果每个地址是平衡树：O(log(N/M))
     * 说好的O(1)呢？
     *
     *
     * 和静态数组⼀样
     * 固定地址空间是不合理的
     * 需要	resize
     *
     * // TODO 哈希表的动态空间处理
     *
     * 平均每个地址承载的元素多过⼀定
     * 程度，即扩容
     * N	/	M	>=	upperTol
     * 平均每个地址承载的元素少过⼀定
     * 程度，即缩容
     * N	/	M	<	lowerTol
     *
     *
     */

    public static void main(String[] args) {
    }
}
