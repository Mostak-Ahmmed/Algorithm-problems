#include <bits/stdc++.h>
using namespace std;

struct Node
{
    int data;
    Node *left, *right;
    Node(int val)
    {
        data = val;
        left = right = NULL;
    }
};

int minDepth(Node *root)
{
    if (root == NULL)
        return 0;
    queue<Node *> q; // create a queue for BFS traversal
    q.push(root);
    int depth = 1; // initialize depth to 1
    while (!q.empty())
    {
        int size = q.size();
        for (int i = 0; i < size; i++)
        {
            Node *node = q.front();
            q.pop();
            if (node->left == NULL && node->right == NULL)
            {
                return depth; // return depth when we find a leaf node
            }
            if (node->left)
            {
                q.push(node->left);
            }
            if (node->right)
            {
                q.push(node->right);
            }
        }
        depth++; // increment depth after processing all nodes at the current level
    }
    return depth;
}

int main()
{
    Node *root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    cout << "Minimum depth of the binary tree is: " << minDepth(root) << endl;
    return 0;
}
