package server.game.collision;

import common.combat.Hitbox;
import common.entity.HittableEntity;
import common.entity.Rect;
import game.util.Vector2D;

import java.util.ArrayList;

public class QuadTree {
    private final QuadTree[] childNodes = new QuadTree[4];
    public final int depth;
    public final Rect bounds;
    public final int maxObjects = 10;

    // Arbitrary value. Should be fixed?
    public static final int MAX_DEPTH = 50;

    // possible fix here.
    private ArrayList<Hitbox> objects = new ArrayList<>();

    /**
     * QuadTree is an algorithm used to check for collisions in an optimal way.
     * You can check Noah's interactive QuadTree here:
     * https://sonic.dawsoncollege.qc.ca/~F201931815/quadtree/
     * @param depth The max number of times that a node can be divided.
     * @param bounds A rectangle representing the node dimensions and position.
     */
    public QuadTree(int depth, Rect bounds) {
        this.depth = depth;
        this.bounds = bounds;
    }

    private void clear() {
        objects.clear();
    }

    private void split() {
        Vector2D subDimensions = new Vector2D(
            this.bounds.getX() / 2,
            this.bounds.getY() / 2
        );

        this.childNodes[0] = new QuadTree(this.depth + 1, new Rect(this.bounds.getX(), this.bounds.getY(), subDimensions));
        this.childNodes[1] = new QuadTree(this.depth + 1, new Rect(this.bounds.getX() + subDimensions.getX(), this.bounds.getY(), subDimensions));
        this.childNodes[2] = new QuadTree(this.depth + 1, new Rect(this.bounds.getX() + subDimensions.getX(), this.bounds.getY(), subDimensions));
        this.childNodes[3] = new QuadTree(this.depth + 1, new Rect( this.bounds.getX() + subDimensions.getX(), this.bounds.getY() + subDimensions.getY(), subDimensions));
    }

    // Implementation can be improved. Objects can be checked better.
    private int getIndex(Hitbox object) {
        int index = -1;

        Rect hRect = object.getRect();

        double verticalMidpoint = this.bounds.getX() + (this.bounds.getWidth() / 2);
        double horizontalMidpoint = this.bounds.getY() + (this.bounds.getY() / 2);

        boolean isTopQuadrant = (hRect.getY() < horizontalMidpoint && hRect.getY() + hRect.getHeight() < horizontalMidpoint);
        boolean isBottomQuadrant = (hRect.getY() > horizontalMidpoint);

        // If the object can fit in the left quadrants
        if (hRect.getX() < verticalMidpoint && hRect.getX() + hRect.getWidth() < verticalMidpoint) {
            if (isTopQuadrant) index = 1;
            else if (isBottomQuadrant) index = 2;
        } else if (hRect.getX() > verticalMidpoint) {
            if (isTopQuadrant) index = 0;
            else if (isBottomQuadrant) index = 3;
        }

        return index;
    }

    private void insert(Hitbox entity) {
        boolean hasChildren = (this.childNodes[0] != null);
        if (hasChildren) {
            int index = this.getIndex(entity);
            if (index != -1) {
                this.childNodes[index].insert(entity);
                return;
            }
        }

        this.objects.add(entity);

        if (this.objects.size() > this.maxObjects && this.depth < QuadTree.MAX_DEPTH) {
            if (!hasChildren) {
                this.split();
            }

            this.objects.add(entity);

            // Loops through all the objects and puts them in the right QuadTree.
            int i = 0;
            while (i < this.objects.size()) {
                Hitbox object = this.objects.get(i);
                int index = this.getIndex(object);
                if (index != -1) {
                    // TODO check if removing them all at the same time is actually faster.
                    this.childNodes[index].insert(object);
                    this.objects.remove(object);
                } else {
                    i++;
                }
            }
        }
    }

    private ArrayList<Hitbox> retrieve(ArrayList<Hitbox> collidables, Hitbox entity) {
       int index = this.getIndex(entity);

       if (index != -1 && this.childNodes[0] != null) {
           this.childNodes[index].retrieve(collidables, entity);
       }

       objects.forEach(collidables::add);

       return collidables;
    }

    /**
     * Entry point to recursive retrieve function.
     * @param entity The entity that will have it's nearby collision checked.
     * @return All the entities in the same quadtree.
     */
    public HittableEntity[] retrieve(Hitbox entity) {
        ArrayList<Hitbox> collisions = this.retrieve(new ArrayList<Hitbox>(), entity);
        return collisions.stream().map(c -> c.owner).toArray(size -> new HittableEntity[size]);
    }

}
