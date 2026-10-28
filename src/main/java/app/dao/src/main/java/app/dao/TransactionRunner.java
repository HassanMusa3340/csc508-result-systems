package app.dao;

/** Runs several writes as one atomic unit. Interface so services can be unit-tested without a DB. */
public interface TransactionRunner {
    void run(TransactionalWork work);
}
