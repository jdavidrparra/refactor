package refactor_test.Business;

import refactor_test.Interfaces.IPaginate;

public class Paginate implements IPaginate {

	private int _rowsPerPage;
	private int _colsNumber;
	private int _pageNumber;
	private int _pageOffset;
	private int _rowOffset;

	public void printPaginateData(final int M, int[] P, int rowsPerPage, int colsNumber) {
		_rowsPerPage = rowsPerPage;
		_colsNumber = colsNumber;
		int colIndex;
		_pageNumber = 1;
		_pageOffset = 1;
		while (_pageOffset <= M) {
			System.out.print("The First ");
			System.out.print(Integer.toString(M));
			System.out.print(" Prime Numbers === Page ");
			System.out.print(Integer.toString(_pageNumber));
			System.out.println("\n");
			for (_rowOffset = _pageOffset; _rowOffset <= _pageOffset + _rowsPerPage - 1; _rowOffset++) {
				for (colIndex = 0; colIndex <= _colsNumber - 1; colIndex++)
					if (_rowOffset + colIndex * _rowsPerPage <= M)
						System.out.printf("%10d", P[_rowOffset + colIndex * _rowsPerPage]);
				System.out.println();
			}
			System.out.println("\f");
			_pageNumber++;
			_pageOffset += _rowsPerPage * _colsNumber;
		}
	}
}
