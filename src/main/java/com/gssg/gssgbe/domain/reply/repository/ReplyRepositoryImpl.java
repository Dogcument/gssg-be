package com.gssg.gssgbe.domain.reply.repository;

import static com.gssg.gssgbe.domain.post.repository.PostRepositoryImpl.SortType.find;
import static com.gssg.gssgbe.domain.reply.entity.QReply.*;
import static com.gssg.gssgbe.domain.reply.repository.ReplyRepositoryImpl.SortType.*;
import static com.querydsl.core.types.Order.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Reply> findAllByPostId(final long postId, final NoOffsetPageRequest pageRequest) {
		final JPAQuery<Reply> jpaQuery = queryFactory
			.selectFrom(reply)
			.limit(pageRequest.getSize());

		if (pageRequest.isSorted()) {
			return jpaQuery
				.where(
					reply.post.id.eq(postId)
				)
				.orderBy(find(pageRequest.getSort()))
				.fetch();
		}

		return jpaQuery
			.where(
				reply.post.id.eq(postId),
				reply.id.lt(pageRequest.getCurrent())
			)
			.orderBy(ID.orderSpecifier)
			.fetch();
	}

	@Getter
	@RequiredArgsConstructor
	public enum SortType {
		ID(new OrderSpecifier<>(DESC, reply.id)),
		LIKE_COUNT(new OrderSpecifier<>(DESC, reply.replyLikes.size())),
		;

		private final OrderSpecifier<?> orderSpecifier;

		public static OrderSpecifier<?> find(final Sort sort) {
			return Arrays.stream(SortType.values())
				.filter(e -> e.name().equals(sort.toString()))
				.map(e -> e.orderSpecifier)
				.findFirst()
				.orElseThrow();
		}
	}
}
