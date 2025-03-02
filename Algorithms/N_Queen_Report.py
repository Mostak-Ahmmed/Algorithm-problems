class NQueen:
    def __init__(self, N):
        self.N = N
        self.solutions = []

    def print_solution(self, board):
        solution = []
        for row in board:
            solution.append(" ".join(map(str, row)))
        self.solutions.append(solution)

    def is_safe(self, board, row, col):
        for i in range(col):
            if board[row][i] == 1:
                return False

        for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
            if board[i][j] == 1:
                return False

        for i, j in zip(range(row, self.N, 1), range(col, -1, -1)):
            if board[i][j] == 1:
                return False

        return True

    def solve_nq_util(self, board, col):
        if col >= self.N:
            self.print_solution(board)
            return True

        res = False
        for i in range(self.N):
            if self.is_safe(board, i, col):
                board[i][col] = 1
                res = self.solve_nq_util(board, col + 1) or res
                board[i][col] = 0  # BACKTRACK

        return res

    def solve_nq(self):
        board = [[0] * self.N for _ in range(self.N)]
        self.solve_nq_util(board, 0)
        return self.solutions


if __name__ == "__main__":
    # 
    n = int(input("Number of queens to place: "))
    queen = NQueen(n)
    solutions = queen.solve_nq()
    for idx, solution in enumerate(solutions):
        print(f"Solution {idx + 1}:")
        for row in solution:
            print(row)
        print()

#  The N-queens puzzle is the problem of placing N queens on a (N×N) chessboard such that no two queens
# can attack each other. Find all distinct solution to the N-queen problem.
# – Hint: For N = 4 there are two possible solutions -