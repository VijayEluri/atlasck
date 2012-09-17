package com.atlasck.repository;

import java.util.List;

import com.atlasck.domain.Answer;

/**
 * Answer interface
 *
 * @author Georgi Lambov
 *
 */
public interface AnswerRepo extends AbstractRepo<Answer> {

	List<Answer> getAll();
}
