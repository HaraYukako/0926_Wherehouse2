package com.example.goods.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.common.transaction.TransactionManager;
import com.example.goods.domain.Goods;
import com.example.goods.exception.GoodsCodeDupulicateException;
import com.example.goods.exception.GoodsDeletedException;
import com.example.goods.exception.NoGoodsException;

public class GoodsServiceImpl implements GoodsService {

	private GoodsRepository goodsRepository;

	public GoodsServiceImpl(GoodsRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}

	@Override
	public void createGoods(Goods goods)
			throws ClassNotFoundException, SQLException, GoodsDeletedException, GoodsCodeDupulicateException {
		try (Connection connection = TransactionManager.getConnection()) {
			try {
				if (goodsRepository.isGoodsDeactive(connection, goods.getCode())) {
					throw new GoodsDeletedException();
				}
				goodsRepository.createGoods(connection, goods);
				TransactionManager.commit(connection);
			} catch (Exception e) {
				TransactionManager.rollback(connection);
				throw e;
			}
		}
	}

	@Override
	public List<Goods> findAllGoods() throws SQLException, NoGoodsException, ClassNotFoundException {
		try (Connection connection = TransactionManager.getReadOnlyConnection()) {
			List<Goods> goodsList = goodsRepository.findAllGoods(connection);
			return goodsList;
		}
	}

	@Override
	public Goods findGoods(int goodsCode) throws SQLException, NoGoodsException, ClassNotFoundException {
		// TODO この部分を埋めて、下記の「return null」を直してください。
		Goods goods = null;
		try (Connection connection = TransactionManager.getConnection()) {
			goods = goodsRepository.findGoods(connection, goodsCode);

			if (goods == null) {
				throw new NoGoodsException();
			}
		}
		return goods;
		// TODO この部分を埋めて、下記の「return null」を直してください。
		/*
		 * コネクションする
		 * findGoods(connection, goodsCode)
		 *
		 * goodsを取得
		 * goods型変数をreturn
		 */
		//return null;
	}

	@Override
	public void deleteGoods(int goodsCode)
			throws SQLException, NoGoodsException, ClassNotFoundException, GoodsDeletedException {
		try (Connection connection = TransactionManager.getConnection()) {
			try {
				if (goodsRepository.isGoodsDeactive(connection, goodsCode)) {
					throw new GoodsDeletedException();
				}
				goodsRepository.deleteGoods(connection, goodsCode);
				TransactionManager.commit(connection);
			} catch (Exception e) {
				TransactionManager.rollback(connection);
				throw e;
			}
		}
	}
}
