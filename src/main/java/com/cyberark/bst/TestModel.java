package com.cyberark.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.graphwalker.java.annotation.GraphWalker;

@GraphWalker(value = "random(edge_coverage(100))")
//@GraphWalker(value = "random(time_duration(1))")

public class TestModel extends PrgContext implements BstModel{

    private static Bst<Integer> _bst;
    private static ArrayList<Integer> _lstNodes;
    private static ArrayList<Integer> _valArray;
    private static ArrayList<Integer> _addedValsArray;
    private static int _currentAddedValueIndex;
    private static int _currentSearchedValueIndex;
    private static boolean _bResult;

    @Override
    public void e_Init(){
        // System.out.println("e_Init");
        _bst = new Bst<Integer>();
        _lstNodes = new ArrayList<Integer>();
        _valArray = new ArrayList<Integer>(Arrays.asList(1, 4, 7, 12, 13, 28, 72));
        _addedValsArray = new ArrayList<Integer>();
        _currentAddedValueIndex = 0;
        _currentSearchedValueIndex = 0;
        _bResult = false;
    }

    @Override
    public void v_Dispatch(){
        // System.out.println("v_Dispatch");
    }

    @Override
    public void e_Dispatch(){
        // System.out.println("e_Dispatch");
    }

    @Override
    public void e_AddNode() {
        System.out.println("e_Add");
        _currentAddedValueIndex = new Random().nextInt(_valArray.size());
        System.out.println( "Adding value: " + _valArray.get(_currentAddedValueIndex) );
        _bst.add(_valArray.get(_currentAddedValueIndex));
        _addedValsArray.add(_valArray.get(_currentAddedValueIndex));
    }

    @Override
    public void v_NodeAdded() {
        System.out.println("v_Added");
        boolean expected = true;
        boolean result = _bst.containValue(_valArray.get(_currentAddedValueIndex));
        assert result == expected:" Value not faound after it was added";
    }

    @Override
    public void e_FindValue() {
        System.out.println("e_FindValue");
        _currentSearchedValueIndex = new Random().nextInt(_addedValsArray.size());
        System.out.println( "Looking for value: " + _addedValsArray.get(_currentSearchedValueIndex) );
        _bResult = _bst.containValue(_addedValsArray.get(_currentSearchedValueIndex));
    }

    @Override
    public void v_ValueFound() {
        System.out.println("v_Found");
        boolean expected = true;
        assert _bResult == expected:" Value: " + _addedValsArray.get(_currentSearchedValueIndex) + " not faound!";
    }

    @Override
    public void e_DeleteLeaf(){
        System.out.println("e_DeleteLeaf");
        _currentSearchedValueIndex = new Random().nextInt(_addedValsArray.size());
        System.out.println( "Trying to delete a leaf with the value: " + _addedValsArray.get(_currentSearchedValueIndex) );
        _bResult = _bst.deleteIfLeaf(_addedValsArray.get(_currentSearchedValueIndex));
    }

    @Override
    public void v_LeafDeleted(){
        System.out.println("v_LeafDeleted");
        boolean expected = true;
        assert _bResult == expected:" Value: " + _addedValsArray.get(_currentSearchedValueIndex) + " not faound or not a leaf!";
    }

    @Override
    public void e_SetTreeNodesList() {
        System.out.println("e_SetTreeNodesList");
        _lstNodes = _bst.listTreeNodes();
    }

    @Override
    public void v_TreeNodesList() {
        System.out.println("v_TreeNodesList");
        System.out.println(_lstNodes);
    }
}
