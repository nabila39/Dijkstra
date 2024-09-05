# Dijkstra Algorithm in Pathfinding Project

## Project Overview

This project utilizes the **Dijkstra algorithm** to find the shortest path between two colleges at Birzeit University. The algorithm is implemented as part of a navigation system designed to help students, faculty, and visitors easily navigate the university campus. By inputting a starting point and a destination, the system calculates the shortest route, ensuring efficient travel across the university grounds.

## About Dijkstra Algorithm

The **Dijkstra algorithm** is a well-known graph search algorithm used to find the shortest path between nodes in a graph. In the context of this project, the university's layout is represented as a graph, where:
- **Nodes** represent different locations on the campus, such as colleges, buildings, or key landmarks.
- **Edges** represent the paths between these nodes, weighted by distances or time taken to travel between them.

### Key Features of Dijkstra Algorithm:

- **Shortest Path Calculation**: Finds the shortest path from the starting college to the destination college.
- **Efficient and Reliable**: Guarantees finding the optimal path due to its greedy approach of exploring the shortest known paths first.
- **Dynamic Updates**: Can adapt to changes in the graph, such as updated distances due to construction or closed paths.

## How It Works in the Project

1. **Graph Representation**:
   - The university is represented as a weighted graph, with nodes corresponding to colleges and edges corresponding to the pathways connecting them. Each edge has a weight that represents the distance or travel time.

2. **Algorithm Execution**:
   - **Initialization**: Start from the selected source college and set the initial distance to 0. Set all other distances to infinity.
   - **Priority Queue**: Use a priority queue to explore nodes with the shortest known distance first.
   - **Relaxation**: For each node, update the shortest known distances to its neighbors if a shorter path is found.
   - **Termination**: The algorithm stops when the shortest path to the destination college is determined.

3. **Example Scenario**:
   - **Input**: User selects "Engineering College" as the starting point and "Science College" as the destination.
   - **Output**: The system calculates the shortest route between the two colleges, highlighting the optimal path on the campus map.

4. **Visualization**:
   - The project features a visual representation of the campus with paths highlighted based on Dijkstra's calculations, providing users with clear guidance.

## Applications and Benefits

- **Campus Navigation**: Helps new students and visitors quickly find their way around the university.
- **Time Efficiency**: Minimizes travel time between buildings, especially useful during tight schedules between classes.
- **Accessibility**: Provides alternative routes when primary paths are blocked due to maintenance or events.

## Technical Details

- **Programming Language**: Java 
- **Data Structures**: Utilizes priority queues (min-heaps) for efficient shortest path calculations.
- **Integration**: Integrated with campus maps for a user-friendly navigation experience.

## Conclusion

The implementation of the Dijkstra algorithm in this project provides a powerful and efficient tool for navigating Birzeit University's campus. By calculating the shortest paths between colleges, it significantly enhances the user experience, making campus navigation faster, easier, and more accessible for everyone.
