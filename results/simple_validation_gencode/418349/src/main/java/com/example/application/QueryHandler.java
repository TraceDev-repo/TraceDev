package com.example.application;

/**
 * Generic interface for handling queries (CQRS pattern).
 * @param <Q> query type
 * @param <R> result type
 */
public interface QueryHandler<Q, R> {
    R handle(Q query);
}