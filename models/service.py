import argparse
import os

parser = argparse.ArgumentParser()

parser.add_argument("-n", "--name", required=True)

args = parser.parse_args()

result = f"""
package ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems.models.*;
import ems.repository.*;

@Service
public class {args.name}Service {'{'}

    @Autowired
    private {args.name}Repository {args.name.lower()}Repository;

    public List<{args.name}> getAll{args.name} () {'{'}
        return {args.name.lower()}Repository.findAll();
    {'}'}

    public List<{args.name}> get{args.name}BySerialNumber (String SerialNumber) {'{'}
        return {args.name.lower()}Repository.find{args.name}BySerialNumber(SerialNumber);
    {'}'}
{'}'}
    """
