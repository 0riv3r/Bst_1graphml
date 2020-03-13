package com.cyberark.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.graphwalker.java.annotation.GraphWalker;

//@GraphWalker(value = "random(edge_coverage(100))")
@GraphWalker(value = "random(time_duration(1))")

public class TestModel extends PrgContext implements BstModel{

    private static Bst<Integer> _bst = new Bst<Integer>();
    private static ArrayList<Integer> _lstNodes = new ArrayList<Integer>();
    private static ArrayList<Integer> _valArray = new ArrayList<Integer>(Arrays.asList(1, 4, 7, 12, 13, 28, 72));
    private static ArrayList<Integer> _addedValsArray = new ArrayList<Integer>();
    private static int _currentAddedValueIndex = 0;
    private static int _currentSearchedValueIndex = 0;
    private static boolean _bResult = false;

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
